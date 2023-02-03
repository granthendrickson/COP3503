// SkipList.java
// Grant Hendrickson (gr251825)
// COP3503C-00317 Fall 22

import java.util.*;

class Node<T extends Comparable<T>>
{
    T data;
    int height;
    ArrayList<Node<T>> nodeArray = new ArrayList<Node<T>>();

    // create a new node with a given height
    // initiallize next references to null (0 through height - 1)
    Node(int height)
    {
        this.data = null;
        this.height = height;

        for (int i = 0; i < height; i++)
            this.nodeArray.add(null);
    }

    // create a new node with a given height and data
    // initiallize next references to null (0 through height - 1)
    Node (T data, int height)
    {
        this.data = data;
        this.height = height;

        for (int i = 0; i < height; i++)
            this.nodeArray.add(null);
    }

    public T value()
    {
        return this.data;
    }

    public int height()
    {
        return this.height;
    }

    public Node<T> next(int level)
    {
        if (this.height <= level)
        {
            return null;
        }

        return this.nodeArray.get(level);
    }

    public void setNext(int level, Node<T> node)
    {
        this.nodeArray.set(level, node);
    }

    public void grow()
    {
        this.height++;
        nodeArray.add(null);
    }

    // grow a node by one 50% of the time
    // and return whether or not the node grew
    public boolean maybeGrow()
    {
        if ((int)(Math.random() * 2) % 2 == 1)
        {
            this.height++;
            nodeArray.add(null);
            return true;
        }

        else
            return false;
    }

    public void trim(int height)
    {
        while (this.height > height)
        {
            // --height to account for zero indexed array
            nodeArray.remove(--this.height);
        }
    }
}

public class SkipList<T extends Comparable<T>>
{
    int size = 0;
    Node<T> head;

    SkipList()
    {
        this.head = new Node<>(1);
    }

    SkipList(int height)
    {
        // make the lowest possible height 1
        if (height < 1)
            this.head = new Node<>(1);
        
        else
            this.head = new Node<>(height);
    }

    public int size()
    {
        return this.size;
    }

    public int height()
    {
        return this.head.height();
    }

    public Node<T> head()
    {
        return this.head;
    }

    public void insert(T data)
    {
        // check if adding a new node causes log_2(n) to exceed the current height
        if ((int)(Math.ceil(Math.log(++this.size) / Math.log(2))) > this.head.height())
        {
            int prevMaxLevel = this.head.height();
            this.head.grow();
            Node<T> curr = head;

            // loop through all nodes that were the previous max height
            // and give them a chance to grow
            while (curr.next(prevMaxLevel) != null)
            {
                // if the node grows 
                if (curr.next(prevMaxLevel).maybeGrow())
                {
                    curr.setNext(prevMaxLevel + 1, curr.next(prevMaxLevel));

                    curr = curr.next(prevMaxLevel);
                }

                else
                    curr = curr.next(prevMaxLevel);
            }
        }

        // generate the new node
        int newNodeHeight = getRandomHeight();
        Node<T> newNode = new Node<>(data, newNodeHeight);

        Node<T> curr = head;
        int level = this.head.height() - 1;

        // start at the height of the skip list
        // then while the data to be inserted is greater than the next node's data
        // move the current pointer forward
        // otherwise if the current level will point to the new node fix the pointers
        for (int i = level; i >= 0; i--)
        {
            while (curr.next(i) != null
                    && curr.next(i).value().compareTo(data) < 0)
                {
                    curr = curr.next(i);
                }
            if (i < newNodeHeight)
            {
                newNode.setNext(i, curr.next(i));
                curr.setNext(i, newNode);
            }
        }
    }

    public void insert(T data, int height)
    {
        while (height > this.head.height())
        {
            this.head.grow();
        }

        // check if adding a new node causes log_2(n) to exceed the current height
        if ((int)(Math.ceil(Math.log(++this.size) / Math.log(2))) > this.head.height())
        {
            int prevMaxLevel = this.head.height();
            this.head.grow();
            Node<T> curr = head;

            // loop through all nodes that were the previous max height
            // and give them a chance to grow
            while (curr.next(prevMaxLevel) != null)
            {
                // if the node grows 
                if (curr.next(prevMaxLevel).maybeGrow())
                {
                    curr.setNext(prevMaxLevel + 1, curr.next(prevMaxLevel));

                    curr = curr.next(prevMaxLevel);
                }

                else
                    curr = curr.next(prevMaxLevel);
            }
        }

        // generate the new node
        int newNodeHeight = height;
        Node<T> newNode = new Node<>(data, newNodeHeight);

        Node<T> curr = head;
        int level = this.head.height() - 1;

        // start at the height of the skip list
        // then while the data to be inserted is greater than the next node's data
        // move the current pointer forward
        // otherwise if the current level will point to the new node fix the pointers
        for (int i = level; i >= 0; i--)
        {
            while (curr.next(i) != null
                    && curr.next(i).value().compareTo(data) < 0)
                {
                    curr = curr.next(i);
                }

            if (i < newNodeHeight)
            {
                newNode.setNext(i, curr.next(i));
                curr.setNext(i, newNode);
            }
        }
    }

    public void delete(T data)
    {
        Node<T> curr = head;
        int level = this.head.height() - 1;

        // check if the node exists
        Node<T> nodeToDelete = getFirstOccurance(data);

        // make sure that the node exists
        if (nodeToDelete == null)
            return;

        int heightOfNodeToDelete = nodeToDelete.height();

        // start at the height of the skip list
        // then while the data to be deleted is greater than the next node's data
        // move the current pointer forward
        // otherwise add current to the drop array and move down a level
        for (int i = level; i >= 0; i--)
        {
            while (curr.next(i) != null
                    && curr.next(i).value().compareTo(data) < 0)
            {
                curr = curr.next(i);
            }
            
            if (i < heightOfNodeToDelete)
                curr.setNext(i, nodeToDelete.next(i));
        }

        // if removing the node causes log_2(n) to fall below the current height
        if (((int)Math.ceil((Math.log(--this.size) / Math.log(2)))) < this.head.height())
        {
            int newHeight = Math.max((int)Math.ceil((Math.log(this.size) / Math.log(2))), 1);

            Node<T> currTrim = head;

            while(currTrim != null)
            {
                currTrim.trim(newHeight);
                
                currTrim = currTrim.next(0);
            }
        }
    }
    
    public boolean contains(T data)
    {
        Node<T> nodeToFind = get(data);

        if (nodeToFind != null)
            return true;
        
        else
            return false; 
    }

    // returns a reference to the first that appears containing data
    public Node<T> get(T data)
    {
        Node<T> curr = head;
        int level = this.head.height() - 1;

        // start at the height of the skip list
        // then while the data to be found is greater than the next node's data
        // move the current pointer forward
        // otherwise add current to the drop array and move down a level
        for (int i = level; i >= 0; i--)
        {
            while (curr.next(i) != null
                    && curr.next(i).value().compareTo(data) < 0)
            {
                curr = curr.next(i);
            }

            if (curr.next(i) != null
                && curr.next(i).value().compareTo(data) == 0)
            {
                // we found a node containing data so return it
                return curr.next(i);
            }
        }

        // we have reached level 0 which means 1 of 2 things:
        // the next node is the value we're searching for
        // the value does not exist in the skip list
        if (curr.next(0) != null
            &&  curr.next(0).value().compareTo(data) == 0)
            {
                // we found the value
                return curr.next(0);
            }
        else
            return null;
    }

    // returns a reference to the first node that contains data
    public Node<T> getFirstOccurance(T data)
    {
        Node<T> curr = head;
        int level = this.head.height() - 1;

        // start at the height of the skip list
        // then while the data to be found is greater than the next node's data
        // move the current pointer forward
        // otherwise add current to the drop array and move down a level
        for (int i = level; i >= 0; i--)
        {
            while (curr.next(i) != null
                    && curr.next(i).value().compareTo(data) < 0)
                {
                    curr = curr.next(i);
                }
        }

        // we have reached level 0 which means 1 of 2 things:
        // the next node is the value we're searching for
        // the value does not exist in the skip list
        if (curr.next(0) != null
            &&  curr.next(0).value().compareTo(data) == 0)
            {
                // we found the value
                return curr.next(0);
            }
        else
            return null;
    }

    public int getRandomHeight()
    {
        int height = 1;

        while(coinFlip() == "heads" && height < this.height())
            height++;

        return height;
    }

    public String coinFlip()
    {
        int random = (int)(Math.random() * 2);

        if (random % 2 == 1)
            return "heads";
        else
            return "tails";
    }

    public static double difficultyRating()
    {
        return 4.2;
    }

    public static double hoursSpent()
    {
        return 12.0;
    }
}