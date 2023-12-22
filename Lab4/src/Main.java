import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class Main {
    public static void main(String[] args) {
     }

    @Nested
    class BSTNodeTest {
        public static Map<Integer, String> map = new LinkedHashMap<>();
        Set<Integer> usedKeys = new HashSet<>();
        public static BSTNode root = null;

        int n = 14;
       @Test
        void test_create() {
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                int key;
                do {
                    key = random.nextInt(20 + n);
                } while (!usedKeys.add(key));
                map.put(key, "Value " + key);
            }
        }

        @Test
        void test_all() {
            generate();
            root.AllBSTNode();
            assertEquals(20, root.getNodeCount(root));
        }

        @Test
        void test_search(){
            generate();
            BSTNode a_root;
            a_root = root.findByKey(root, 14);
            assertEquals("Value 14", a_root.getValue());
            a_root = root.findByKey(root, 26);
            assertEquals("Value 26", a_root.getValue());
            a_root = root.findByKey(root, 32);
            assertEquals("Value 32", a_root.getValue());
            a_root = root.findByKey(root, 45);
            assertNull(a_root);

        }


        @Test
        void test_insert()
        {
            BSTNode a_root;
            int del_st = (2*n+20)/2;
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                int key;
                do {
                    key = random.nextInt(20) + n;
                } while (!usedKeys.add(key));
                map.put(key, "Value " + key);
            }
            for (int key : map.keySet()) {
                if (key != del_st)
                    root.insert(root, key, map.get(key));
            }
            root.insert(root, del_st, "Value " + del_st);
            a_root = root.findByKey(root, del_st);
            assertEquals("Value " + del_st, a_root.getValue());
            assertEquals(map.size(), root.getNodeCount(root));
            root.insert(root, 20, "Value (new updated)");
            a_root = root.findByKey(root, 20);
            assertEquals("Value (new updated)", a_root.getValue());
        }
        @Test
        void test_delete()
        {
            BSTNode a_root;
            generate();
            int del_st = (2*n+20)/2;
            root.DeleteByKey(root,del_st);
            assertEquals(map.size()-1, root.getNodeCount(root));
            a_root = root.findByKey(root, del_st);
            assertNull(a_root);
        }

        public void generate(){
            root = new BSTNode(n, "Value " + n);
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                int key;
                do {
                    key = random.nextInt(20) + n;
                } while (!usedKeys.add(key));
                map.put(key, "Value " + key);
            }
            for (int key : map.keySet()) {
                root.insert(root, key, map.get(key));
            }
        }

    }

    public static class BSTNode {

        private int key;
        private String value;
        private BSTNode leftChild;
        private BSTNode rightChild;

        private int nodeCount;

        public BSTNode(int key, String value) {
            this.key = key;
            this.value = "Value " + key;
            this.leftChild = null;
            this.rightChild = null;
            this.nodeCount = 0;
        }
        public int getKey() {
            return this.key;
        }

        public int getNodeCount(BSTNode root){
            if (root == null)
                return 0;
            return 1 + getNodeCount(root.leftChild) + getNodeCount(root.rightChild);
        }

        public String getValue() {
            return value;
        }

        public BSTNode getLeftChild() {
            return this.leftChild;
        }

        public void setLeftChild(BSTNode leftChild) {
            this.leftChild = leftChild;
        }

        public BSTNode getRightChild() {
            return this.rightChild;
        }

        public void setRightChild(BSTNode rightChild) {
            this.rightChild = rightChild;
        }

        public static void insert(BSTNode root, int key, String value) {
             if (key < root.key) {
                if (root.leftChild == null) {
                    root.leftChild = new BSTNode(key, value);
                } else {
                    insert(root.leftChild, key, value);
                }
            } else if (key > root.key) {
                if (root.rightChild == null) {
                    root.rightChild = new BSTNode(key, value);
                } else {
                    insert(root.rightChild, key, value);
                }
            } else {
                root.value = value;
            }
        }

        public void DeleteByKey(BSTNode root, int key) {
            root = remove(root, key);
        }

        private BSTNode remove(BSTNode root, int key) {
            if (root == null) {
                return null;
            }

            if (key < root.key) {
                root.leftChild = remove(root.leftChild, key);
            } else if (key > root.key) {
                root.rightChild = remove(root.rightChild, key);
            } else {
                if (root.leftChild == null) {
                    return root.rightChild;
                } else if (root.rightChild == null) {
                    return root.leftChild;
                }
                int minValue = root.key;
                while (root.leftChild != null) {
                    minValue = root.leftChild.key;
                    root = root.leftChild;
                }
                root.key = minValue;
               root.rightChild = remove(root.rightChild, minValue);
            }

            return root;
        }

        public static BSTNode findByKey(BSTNode root, int key) {
            if (root == null)
                return null;
            if (key == root.key) {
                return root;
            } else if (key < root.key && root.leftChild != null) {
                return findByKey(root.leftChild, key);
            } else if (key > root.key&& root.rightChild != null) {
                return findByKey(root.rightChild, key);
            } else {
                return null;
            }
        }
        public void AllBSTNode() {
            if (leftChild != null) {
                leftChild.AllBSTNode();
            }
            System.out.printf("Key: " + key + ", " + value + "\n");
            if (rightChild != null) {
                rightChild.AllBSTNode();
            }
        }

    }
    }