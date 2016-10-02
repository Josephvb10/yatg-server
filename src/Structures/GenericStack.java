package Structures;

public class GenericStack<T> {

		private GenericNode<T> head;
		
		public GenericStack(){
			this.head = null;
		}
		public GenericStack(T data){
			GenericNode<T> newNode = new GenericNode<>(data);
			this.head = newNode;
		}
		public void push(T data){
			GenericNode<T> newNode = new GenericNode<>(data);
			push(newNode);
		}
		
		public void push(GenericNode<T>  newNode){
			if(getHead() == null){
				setHead(newNode);
			}
			else{
				newNode.setNext(getHead());
				setHead(newNode);
			}
		}
		
		
		public T pop(){
			T result = null;
			if (getHead() != null){
				result = head.getData();
				this.head = getHead().getNext();
			}
			return result;
		}

		
		public GenericNode<T> getHead() {
			return head;
		}
		
		public boolean isEmpty(){
			return head==null;			
		}
		

		public void setHead(GenericNode<T> head) {
			this.head = head;
		}
		
		  @Override
		  public String toString() {
			 String result="(";
			 GenericNode<T> current = this.getHead(); 
			 while(current != null){
		   result+=current.getData().toString()+", ";
		   current=current.getNext();

		    }
			 result+=")";
			return result;
		  }
		

	}

