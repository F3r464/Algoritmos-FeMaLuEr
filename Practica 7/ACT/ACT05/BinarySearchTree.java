package ACT.ACT05;
import ACT.ACT04.*;
public interface BinarySearchTree<E extends Comparable<E>>{
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
}
