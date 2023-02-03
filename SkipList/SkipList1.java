import java.util.*;
import java.util.ArrayList;

// NOTES:

class Node<T extends Comparable<T>>
{
    T data;
    int height;
    ArrayList<Node<T>> nodeArray = new ArrayList<Node<T>>();

    // create a new node with a given height
    Node(int height)
    {
        this.data = null;
        this.height = height;

        for (int i = 0; i <= height; i++)
        {
            this.nodeArray.add(null);
        }
    }

    // create a new node with a given height and data
    Node(T data, int height)
    {
        this.data = data;
        this.height = height;

        for (int i = 0; i <= height; i++)
        {
            this.nodeArray.add(null);
        }
    }

    public T value()
    {
        return this.data;
    }

    public int height()
    {
        return this.height;
    }

    // returns reference to the next node at the given level
    public Node<T> next(int level)
    {
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

    // 50% chance of growing the node
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
            nodeArray.remove(this.height);
            this.height--;
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
        if (height < 1)
            this.head = new Node<>(1);

        else
            this.head = new Node<>(height);
    }

    public int size()
    {
        return size;
    }

    public int height()
    {
        return this.head.height;
    }

    public Node<T> head()
    {
        return this.head;
    }

    // insert data with a random height
    public void insert (T data)
    {
        // check if adding a new node causes log_2(n) to exceed the current height
        if ((int)(Math.log(++this.size) / Math.log(2)) > this.head.height())
        {
            head.grow();

            int prevMaxLevel = head.height() - 1;
            Node<T> curr = head;

            while (curr != null)
            {
                // if we grow the node
                if (curr.next(prevMaxLevel).maybeGrow())
                {
                    curr.setNext(prevMaxLevel + 1, curr.next(prevMaxLevel));

                    curr = curr.next(prevMaxLevel);
                }
                
                // if we don't grow the node
                else
                    curr = curr.next(prevMaxLevel);
            }
        }

        Node<T> curr = head;
        int currLevel = head.height();

        ArrayList<Node<T>> dropArray = new ArrayList<Node<T>>();

        boolean spotFound = false;

        while(!spotFound)
        {
            // if the value that the current next is pointing to 
            // is greater than or equal to the value to be inserted
            // then move down a level
            if (curr.next(currLevel).value().compareTo(data) >= 0)
            {
                dropArray.add(curr);
                currLevel--;
                
                // we have reached the bottom of the current node and therefore found the spot to insert
                if (currLevel == 0)
                {
                    // insert
                    int newNodeHeight = getRandomHeight();

                    Node<T> newNode = new Node<>(data, newNodeHeight);
                    
                    // correct pointers
                    for (int i = newNodeHeight - 1; i >= 0; i--)
                    {
                        newNode.setNext(i, dropArray.get(i).next(i));
                        dropArray.get(i).setNext(i, newNode);
                    }

                    spotFound = true;
                }
            }

            // if the value that the current next is pointing to 
            // is less than the value to be inserted
            // move to the next pointer at that level
            if (curr.next(currLevel).value().compareTo(data) < 0)
                curr = curr.next(currLevel);
        }
    }

    // insert data with a given height
    public void insert (T data, int height)
    {
        if (height < 1)
            height = 1;

        // check if adding a new node causes log_2(n) to exceed the current height
        if ((int)(Math.log(++this.size) / Math.log(2)) > this.head.height())
        {
            head.grow();

            int prevMaxLevel = head.height() - 1;
            Node<T> curr = head;

            while (curr != null)
            {
                // if we grow the node
                if (curr.next(prevMaxLevel).maybeGrow())
                {
                    curr.setNext(prevMaxLevel + 1, curr.next(prevMaxLevel));

                    curr = curr.next(prevMaxLevel);
                }
                
                // if we don't grow the node
                else
                    curr = curr.next(prevMaxLevel);
            }
        }

        Node<T> curr = head;
        int currLevel = head.height();

        ArrayList<Node<T>> dropArray = new ArrayList<Node<T>>();

        boolean spotFound = false;

        while(!spotFound)
        {
            if (curr.next(currLevel) == null)
            {
                dropArray.add(curr);
                currLevel--;

                // we have reached the bottom of the current node and therefore found the spot to insert
                if (currLevel == 0)
                {
                    // insert
                    Node<T> newNode = new Node<>(data, height);
                    
                    // correct pointers
                    for (int i = height - 1; i >= 0; i--)
                    {
                        newNode.setNext(i, dropArray.get(i).next(i));
                        dropArray.get(i).setNext(i, newNode);
                    }

                    spotFound = true;
                }
            }

            // if the value that the current next is pointing to 
            // is greater than or equal to the value to be inserted
            // then move down a level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) >= 0)
            {
                dropArray.add(curr);
                currLevel--;
                
                // we have reached the bottom of the current node and therefore found the spot to insert
                if (currLevel == 0)
                {
                    // insert
                    Node<T> newNode = new Node<>(data, height);
                    
                    // correct pointers
                    for (int i = height - 1; i >= 0; i--)
                    {
                        newNode.setNext(i, dropArray.get(i).next(i));
                        dropArray.get(i).setNext(i, newNode);
                    }

                    spotFound = true;
                }
            }

            // if the value that the current next is pointing to 
            // is less than the value to be inserted
            // move to the next pointer at that level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) < 0)
            {
                curr = curr.next(currLevel);
            }
        }
    }

    public void delete(T data)
    {
        Node<T> curr = head;
        int currLevel = head.height();

        ArrayList<Node<T>> dropArray = new ArrayList<Node<T>>();

        while (curr != null)
        {
            // if the value that the current next is pointing to 
            // is greater than or equal to the value to be deleted
            // then move down a level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) >= 0)
            {
                dropArray.add(curr);
                currLevel--;
                
                // either the next node is the value to be deleted
                // or it does not exist
                if (currLevel == 0)
                    // if the value does exist
                    if (curr.next(currLevel) != null &&
                        curr.next(currLevel).value().compareTo(data) == 0)
                    {
                        // write over the pointers to remove the node
                        for (int i = curr.next(currLevel).height() - 1; i >= 0; i--)
                        {
                            dropArray.get(i).setNext(i, curr.next(i));
                        }
                        
                        // if removing the node causes log_2(n) to fall below the current height
                        if ((int)(Math.log(--this.size) / Math.log(2)) < this.head.height())
                        {
                            int newHeight = (int)(Math.log(--this.size) / Math.log(2));

                            Node<T> currTrim = head;

                            while (currTrim != null)
                            {
                                currTrim.trim(newHeight);
                                currTrim = currTrim.next(0);
                            }
                        }
                    }

                    // the value does not exist
                    else
                        return;
            }

            // if the value that the current next is pointing to is less than the value to be deleted
            // move to the next pointer at that level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) < 0)
            {
                curr = curr.next(currLevel);
            }
        }
    }

    public boolean contains(T data)
    {
        Node<T> curr = head;
        int currLevel = head.height();

        while (curr != null)
        {
            // if the value that the current next is pointing to 
            // is greater than or equal to the value to be found
            // then move down a level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) >= 0)
            {
                currLevel--;
                
                // either the next node is the value to be found
                // or it does not exist
                if (currLevel == 0)
                    // if the value does exist
                    if(curr.next(currLevel).value().compareTo(data) == 0)
                    {
                        return true;
                    }

                    // the value does not exist
                    else
                        return false;
            }

            // if the value that the current next is pointing to is less than the value to be found
            // move to the next pointer at that level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) < 0)
            {
                curr = curr.next(currLevel);
            }
        }

        return false;
    }

    public Node<T> get(T data)
    {
        Node<T> curr = head;
        int currLevel = head.height();

        while (curr != null)
        {
            // if the value that the current next is pointing to 
            // is greater than or equal to the value to be found
            // then move down a level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) >= 0)
            {
                currLevel--;
                
                // either the next node is the value to be found
                // or it does not exist
                if (currLevel == 0)
                    // if the value does exist
                    if (curr.next(currLevel) != null &&
                        curr.next(currLevel).value().compareTo(data) == 0)
                    {
                        return curr.next(currLevel);
                    }

                    // the value does not exist
                    else
                        return null;
            }

            // if the value that the current next is pointing to is less than the value to be found
            // move to the next pointer at that level
            if (curr.next(currLevel) != null &&
                curr.next(currLevel).value().compareTo(data) < 0)
            {
                curr = curr.next(currLevel);
            }
        }

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
        return 4.0;
    }

    public static double hoursSpent()
    {
        return 6.0;
    }
}