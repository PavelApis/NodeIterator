import java.util.*;

class Node {
    // unique
    public int value;
    // collection of nodes such that for each sibling in
    // colection edge between this node and sibling exists
    public Collection<Node> siblings;

    public Node (int value, Collection<Node> siblings) {
        this.value = value;
        this.siblings = siblings;
    }
}

class NodeIterator implements Iterator<Node> {

    //queue of nodes, that will be iterated
    Queue<Node> queue;

    //array of unique values of iterated nodes
    ArrayList<Integer> iteratedValues;

    //create empty array of unique values
    //and queue with start node
    public NodeIterator(Node start) {
        this.iteratedValues = new ArrayList<>();
        this.queue = new LinkedList<>();
        queue.add(start);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    //returns node at the front of queue and remove it from queue
    //add its value to iterated values and add its siblings to queue
    @Override
    public Node next() {
        if(hasNext() == false){
            throw new NoSuchElementException();
        }
        Node next = queue.remove();
        iteratedValues.add(next.value);
        for(Node sibling : next.siblings){
            if(!iteratedValues.contains(sibling.value)){
                queue.add(sibling);
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Node start = new Node(1, Arrays.asList(
                new Node(2, Collections.emptyList()),
                new Node(3, Collections.singleton(
                        new Node(4, Collections.emptyList()))
                )
        ));

        NodeIterator iterator = new NodeIterator(start);
        // should print numbers 1-4 in any order
        while (iterator.hasNext()) {
            System.out.println(iterator.next().value);
        }
    }
}
