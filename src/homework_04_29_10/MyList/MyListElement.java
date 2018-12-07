package homework_04_29_10.MyList;

class MyListElement<T> {
    private T element;
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
        return element.getClass().getSimpleName()+"{"+ element.toString()+"}";
    }

    MyListElement(T object) {
        this.element = object;
    }

    T getElement() {
        return element;
    }

    MyListElement<T> getNextMyListElement() {
        return nextMyListElement;
    }

    void setNextMyListElement(MyListElement<T> nextMyListElement) {
        this.nextMyListElement = nextMyListElement;
    }
}