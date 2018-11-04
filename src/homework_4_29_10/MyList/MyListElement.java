package homework_4_29_10.MyList;

class MyListElement<T> {
    private T object;
    private MyListElement<T> nextMyListElement;

    void addNextMyListElement(T object){
        if(this.nextMyListElement !=null)
            this.nextMyListElement.addNextMyListElement(object);
        else
            this.nextMyListElement = new MyListElement<>(object);
    }

    MyListElement<T> recursiveReverse(){
        if(this.getNextMyListElement()!=null) {
            MyListElement<T> newFirst = this.getNextMyListElement().recursiveReverse();
            this.getNextMyListElement().setNextMyListElement(this);
            return newFirst;
        }
        else
            return this;
    }


    @Override
    public String toString(){
        return object.getClass().getSimpleName()+"{"+object.toString()+"}";
    }

    MyListElement(T object) {
        this.object = object;
    }

    T getObject() {
        return object;
    }

    MyListElement<T> getNextMyListElement() {
        return nextMyListElement;
    }

    void setNextMyListElement(MyListElement<T> nextMyListElement) {
        this.nextMyListElement = nextMyListElement;
    }
}