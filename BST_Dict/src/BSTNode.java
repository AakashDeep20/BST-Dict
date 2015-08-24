/* 
 Implementation of a user fed dictionary using a Binary Search Tree (BST).
 Each word in the dictionary along with its meaning is represented as a single node of the tree.
 Methods for insertion, deletion, search and traversal operations are provided as they are performed in 
 a Binary Search Tree.
 
  Aakash Deep Sahariya
  IIT-BHU
 */

import java.util.Scanner;

public class BSTNode {
	
	        String word ;
	        String meaning ;
	        BSTNode left, right;

	static BSTNode root = null;

	  BSTNode  createNode(String word, String meaning) {
	       BSTNode newnode = new BSTNode();
	        
	        newnode.word = word;
	        newnode.meaning = meaning;
	        newnode.left = newnode.right = null;
	        return newnode;
	  }
	  
  void insert(String word, String meaning) {
	        BSTNode parent =  null;
	        BSTNode current = null;
	        BSTNode newnode = null;
	        int res = 0;
	        if (root == null) {
	                root = createNode(word, meaning);
	                return;
	        }
	        for (current = root; current != null;
	           current = (res > 0) ? current.right : current.left) {
	                res = word.toString().compareToIgnoreCase(current.word.toString());
	                if (res == 0) {
	                        System.out.println("Duplicate entry!");
	                        return;
	                }
	                parent = current;
	        }
	        newnode = createNode(word, meaning);
	       if(res > 0)
	    	   parent.right = newnode;
	    	   else
	    		   parent.left = newnode;
	        return;
	  }

 public void deleteNode(String str) {
		  BSTNode parent =  null;
	        BSTNode current = null;
	        BSTNode temp = null;
	        int flag = 0, res = 0;
	        if (root == null) {
	        	System.out.println("Dictionary is Empty!");
                return;
        }
        current = root;
        while (true) {
        	res = current.word.compareToIgnoreCase(str);
                if (res == 0)
                        break;
                flag = res;
                parent = current;
                current = (res > 0) ? current.left : current.right;
                if (current == null)
                        return;
        }
        /* deleting leaf node */
        if (current.right == null) {
                if (current == root && current.left == null) {
                        
                        root = null;
                        return;
                } else if (current == root) {
                        root = current.left;
                        return;
                }

                if(flag > 0) 
                	parent.left = current.left;
                else
                	parent.right = current.left;
        } else {
                /* delete node with single child */
                temp = current.right;
                if (temp.left == null) {
                        temp.left = current.left;
                        if (current == root) {
                                root = temp;
                                return;    }
                        if(flag > 0)
                        	parent.left = temp;
                        else
                            parent.right = temp;
                } else {
                        /* delete node with two children */
                        BSTNode successor = null;
                        while (true) {
                                successor = temp.left;
                                if (successor.left == null)
                                        break;
                                temp = successor;
                        }
                        temp.left = successor.right;
                        successor.left = current.left;
                        successor.right = current.right;
                        if (current == root) {
                                root = successor;
                                return;
                        }
                        if(flag > 0)
                        	parent.left = successor;
                        else
                            parent.right = successor;
                }
        }
        return;
  }

 public void findElement(String str) {
        BSTNode temp = null;
        int flag = 0, res = 0;
        if (root == null) {
                System.out.println("Dictionary is out of station!");
                return;
        }
        temp = root;
        while (temp != null) {
        	res = temp.word.compareToIgnoreCase(str);
                if (res == 0) {
                        System.out.println("Word   : " + str);
                        System.out.println("Meaning: "+temp.meaning);
                        flag = 1;
                        break;
                }
                temp = (res > 0) ? temp.left : temp.right;
        }
        if (flag == 0)
                System.out.println("Search Element not found in the Dictionary");
        return;
  }

 public void inorderTraversal(BSTNode myNode) {
        if (myNode != null) {
                inorderTraversal(myNode.left);
                System.out.println("Word    : "+ myNode.word);
                System.out.println("Meaning : "+ myNode.meaning);
                System.out.println();
                inorderTraversal(myNode.right);
        }
        return;
  }
  

public static void main(String args[]) {
        BSTNode ob = new BSTNode();
        int ch;
        String  str;
        String meaning ;
        Scanner in = new Scanner(System.in);
        
        while (true) {
        	System.out.println("\n1. Insertion\t2. Deletion");
        	System.out.println("3. Searching\t4. Traversal");
                ch = Integer.parseInt(in.nextLine());
               
                switch (ch) {
                        case 1:
                        	System.out.print("Word to insert:");
                                str = in.nextLine();
                                System.out.println("Meaning:");
                                meaning = in.nextLine();
                                ob.insert(str, meaning);
                                break;
                        case 2:
                        	System.out.print("Enter the word to delete:");
                        		str = in.nextLine();
                                ob.deleteNode(str);
                                break;
                                
                        case 3:
                        	System.out.print("Enter the search word:");
                        	str = in.nextLine();
                            ob.findElement(str);
                            break;
                            
                    case 4:
                            ob.inorderTraversal(root);
                            break;
                            
                    default:
                    	System.out.println("You have entered wrong option");
                            break;
            }
                
    }
}
}

/*Sample Output:
 
 
1. Insertion	2. Deletion
3. Searching	4. Traversal
1
Word to insert:Aakash
Meaning:
Deep

1. Insertion	2. Deletion
3. Searching	4. Traversal
1
Word to insert:Lionel
Meaning:
Messi

1. Insertion	2. Deletion
3. Searching	4. Traversal
1
Word to insert:Alexis
Meaning:
Sanchez

1. Insertion	2. Deletion
3. Searching	4. Traversal
1
Word to insert:Andres
Meaning:
Iniesta

1. Insertion	2. Deletion
3. Searching	4. Traversal
1
Word to insert:Steven
Meaning:
Gerrard

1. Insertion	2. Deletion
3. Searching	4. Traversal
3
Enter the search word:Aakash
Word   : Aakash
Meaning: Deep

1. Insertion	2. Deletion
3. Searching	4. Traversal
1
Word to insert:Roger
Meaning:
Federer

1. Insertion	2. Deletion
3. Searching	4. Traversal
4
Word    : Aakash
Meaning : Deep

Word    : Alexis
Meaning : Sanchez

Word    : Andres
Meaning : Iniesta

Word    : Lionel
Meaning : Messi

Word    : Roger
Meaning : Federer

Word    : Steven
Meaning : Gerrard


*/
