import java.util.BitSet;

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree(int[] arr) {
        root = sortedArrayToBST(arr, 0, arr.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);

        node.left = sortedArrayToBST(arr, start, mid - 1);
        node.right = sortedArrayToBST(arr, mid + 1, end);

        return node;
    }

    public TreeNode findElement(int value) {
        return findElement(root, value);
    }

    private TreeNode findElement(TreeNode node, int value) {
        if (node == null || node.value == value)
            return node;

        if (value < node.value)
            return findElement(node.left, value);
        else
            return findElement(node.right, value);
    }

    public TreeNode findSequence(String sequence) {
        return findSequence(root, sequence.toCharArray(), 0);
    }

    private TreeNode findSequence(TreeNode node, char[] sequence, int index) {
        if (node == null) {
            return null;
        }

        if (node.value == sequence[index]) {
            if (index == sequence.length - 1){
                return node;
            }
            index++;
            TreeNode left = findSequence(node.left, sequence, index);
            TreeNode right = findSequence(node.right, sequence, index);
            return left != null ? left : right;
        } else {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        
        int[] arr = {4, 7, 2, 1, 9, 5, 8, 3, 6, 10};
        BinaryTree binaryTree = new BinaryTree(arr);

        TreeNode foundElement = binaryTree.findElement(5);
        if (foundElement != null) {
            System.out.println("Element found: " + foundElement.value);
        } else
            System.out.println("Element not found.");

        TreeNode foundSequence = binaryTree.findSequence("689");
        if (foundSequence != null) {
            System.out.println("Sequence found, starting from element: " + foundSequence.value);
        } else  System.out.println("Sequence not found.");

        BitSet bitSet = new BitSet(10);
        bitSet.set(1);
        bitSet.set(3);
        bitSet.set(5);
        System.out.println("BitSet: " + bitSet);

        bubbleSort(arr);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
