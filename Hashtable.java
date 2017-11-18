import java.util.NoSuchElementException;

public class Hashtable{
	//-------------------------------------------------------
	//Inner class
	//-------------------------------------------------------
	public class HashNode<k,v>{
		private String key;
		private String value;
		HashNode next;
		
		//--------------------------------------------------
		//constructor
		//class: hash node class
		//args: String key, value
		//--------------------------------------------------
		public HashNode(String key, String value){
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	//--------------------------------------------------
	//Datatypes for the class Hashtable
	//private declaration
	//--------------------------------------------------
	private HashNode[] arr;
	private int size;
	//--------------------------------------------------
	//constructor 
	//class: hash table
	//initializes an array of hash nodes. 
	//--------------------------------------------------
	public Hashtable(){
		arr = new HashNode[10000000];
		size = 0;
	}
	//--------------------------------------------------
	//Hash function
	//--------------------------------------------------
	/*Function: hash
	 * Arguments: key, size
	 * Returns: int index
	 */
	public int hash(String key, int size){
		//using hashCode() to get integer of string 
		int hash = (Math.abs(key.hashCode()))%size;
		return hash;
	}
	//--------------------------------------------------
	//Private helper method.
	//Methods that this method helps are: get(), containsKey() and put()
	//--------------------------------------------------
	private HashNode getNode(String key){
		int hash = hash(key, arr.length);
		HashNode curr = arr[hash];
		
		while (curr != null){
			if (curr.key == key){
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}
	//--------------------------------------------------
	//Returns the value of the hash node
	//--------------------------------------------------
	public String get(String key){
		HashNode temp = getNode(key);
		if (temp != null){
			return temp.value;
		}
		return null;
	}
	//--------------------------------------------------
	//Returns true is get(key) contains the value of the node. Returns false if the method get(0) returns null.
	//--------------------------------------------------
	public boolean containsKey(String key){
		return get(key) != null;	
	}
	
	//--------------------------------------------------
	//Method puts a node in the array of hash nodes. It does not add duplicates by first checking 
	//if the current(temp) node is not empty. If the node is empty, then we only want to change the
	//value of the node. This is because every key should only have one value. A key should not map
	//to two values.
	//If the current node is empty, then we make a new hash node and add it. 
	//Finally, we increase the size.
	//--------------------------------------------------
	public void put(String key, String value){
		int hash = hash(key, arr.length);
		HashNode head = arr[hash];
		
		HashNode temp =getNode(key);
		
		if (temp!= null){
			temp.value= value;
		}else{
			HashNode n = new HashNode(key, value);
			n.next = head;
			arr[hash] = n;
		}
		size++;
	}
	//--------------------------------------------------
	//Remove function removes a hash node from the array. It removes the key and value completely. 
	//The function moves through the list of hash nodes and updates the pointers prev and curr.
	//The function considers two cases:
	//	1) When we remove the head, then prev will be set to null
	//	2) When we remove some hash node in the array of hash nodes
	//--------------------------------------------------
	public String remove(String key) throws NoSuchElementException{
		int hash = hash(key, arr.length);
		HashNode head = arr[hash];
		String removed = null;
		
		if (head == null){
			throw new NoSuchElementException();
		}
		
		HashNode prev = null;
		HashNode curr = head;
		
		while (curr != null){
			if (curr.key == key){
				removed = curr.value;
				if (prev != null){
					prev.next = curr.next;
				}else{
					arr[hash] =curr.next;
				}
			}
			prev = curr;
			curr = curr.next;
		}
		return removed;
	}
	
}	
