package homework_3_26_10.task2_26_10;

public class NodeList {
   Node first;
   int length;
   public void add(Node node){
       if(first!=null) {
           Node next = first;
           while (next.nextNode != null) {
               next = next.nextNode;
           }
           next.nextNode = node;
           next.nextNode.index = next.index+1;
           length++;
       }else{
           first = node;
           first.index = 0;
           length = 1;
       }

   }
   public String toString(){
       StringBuilder string = new StringBuilder();
       Node next = first;
       while(next!=null){
           string.append("Index: ").append(next.index).append("\t").append("Value: ").append(next.value).append("\n");
           next = next.nextNode;
       }
       return string.toString();
   }
   public void removeByValue(int value){
       Node current = first.nextNode;
       Node previous = first;
       while(current!=null){
           if(current.value == value){
               previous.nextNode = current.nextNode;
           }
           else{
               previous = previous.nextNode;
           }
           current = current.nextNode;
       }
       this.UpdateIndexes();
   }
   public void removeByIndex(int index){
       if(first!=null||index>length-1) {
           if(index==0){
               first = first.nextNode;
           }
           else{
               Node current = first;
               Node previous = null;
               for(int i=0;i<index;i++){
                   previous = current;
                   current = current.nextNode;
               }
               previous.nextNode = current.nextNode;
           }
           this.UpdateIndexes();
       }
       else
           System.out.println("Массив пуст");

   }
   void UpdateIndexes(){
       Node current = first;
       int i =0;
       while(current!=null){
           current.index = i;
           current = current.nextNode;
           i++;
       }
   }
}
