package homework_4_29_10.MyList;

class MyListElement<T> {
    private T object;
    private int index;
    private MyListElement<T> nextMyListElement;

    void addNextMyListElement(T object){
        if(this.nextMyListElement !=null)
            this.nextMyListElement.addNextMyListElement(object);
        else
            this.nextMyListElement = new MyListElement<>(object,this.index+1);
    }

    MyListElement<T> recursiveReverse(int length){
        this.index = length-1-index;
        if(this.getNextMyListElement()!=null) {
            MyListElement<T> newFirst = this.getNextMyListElement().recursiveReverse(length);
            this.getNextMyListElement().setNextMyListElement(this);
            return newFirst;
        }
        else
            return this;
    }

    void changeIndex(int plusMinusOne){
        if(this.nextMyListElement !=null)
            this.nextMyListElement.changeIndex(plusMinusOne);
        this.index = index+plusMinusOne;
    }

    @Override
    public String toString(){
        return "Index: "+index+"\t"+object.getClass().getSimpleName()+": "+object.toString();
    }

    MyListElement(T object, int index) {
        this.object = object;
        this.index = index;
    }

    T getObject() {
        return object;
    }

    void setObject(T object) {
        this.object = object;
    }

    int getIndex() {
        return index;
    }

    void setIndex(int index) {
        this.index = index;
    }

    MyListElement<T> getNextMyListElement() {
        return nextMyListElement;
    }

    void setNextMyListElement(MyListElement<T> nextMyListElement) {
        this.nextMyListElement = nextMyListElement;
    }
}