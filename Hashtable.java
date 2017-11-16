import java.util.NoSuchElementException;

public class Hashtable{
	
	public class HashNode<k,v>{
		private String key;
		private String value;
		HashNode next;
		
		//--------------------------------------------------
		//constructor
		//--------------------------------------------------
		public HashNode(String key, String value){
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
//		public k getKey(){
//			return key;
//		}
//		
//		public v getValue(){
//			return value;
//		}
	}
	
	private HashNode[] arr;
	private int size;
	//--------------------------------------------------
	//constructor
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
	
	
	public boolean containsKey(String key){
		return get(key) != null;
		
	}
	public HashNode getNode(String key){
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
	
	public String get(String key){
		HashNode temp = getNode(key);
		if (temp != null){
			return temp.value;
		}
		return null;
	}
	
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
//					head = curr.next;
				}
			}
			prev = curr;
			curr = curr.next;
		}
		return removed;
	}
	
}	
