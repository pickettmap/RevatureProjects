package com.intellijeep.util;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * Requirements:
 * No duplicates
 * Generic
 * No Gaps
 * Unordered
 * Dynamically Sized
 * View all elements
 */

public class IntelliJeepCollection<T> implements Iterable<T>{

    private T[] objList;
    private int currentSize = 0;

    // Takes an array of objects. Can be used for primitives or custom classes
    public IntelliJeepCollection(T[] objList) {
        this.objList = objList;
    }

    // TODO: Try and make safe converter from Object -> Type (which converts back to object?)
    // Requires object type and size, used for custom classes
    public IntelliJeepCollection(Class<T> clazz, int capacity) {
        objList = (T[]) Array.newInstance(clazz, capacity);
        currentSize = capacity;
    }

    public T get(T o) {
        for(int i = 0; i < currentSize; i++) {
            if(objList[i].equals(o)){
                return o;
            }
        }
        return null;
    }

    public void add(T o) {
        //TODO: maybe refactor this
        //Poor way of checking for duplicates
        for(T obj : objList) {
            if(obj == null) {continue;}
            if(obj.equals(o)) {
                return;
            }
        }

        T[] newArray = (T[]) Array.newInstance(o.getClass(), objList.length + 1);
        System.arraycopy(objList, 0, newArray, 0, objList.length);
        newArray[objList.length] = o;
        objList = newArray;
        currentSize = objList.length;
    }

    public int size() {
        return currentSize;
    }

    public void remove(T o) {
        T[] tempList = (T[]) Array.newInstance(o.getClass(), currentSize-1);

        for(int i = 0, k = 0; i < currentSize; i++) {
            if(objList[i].equals(o)) {
                continue; //skip this object
            }
            tempList[k++] = objList[i]; //add everything else to new array
        }
        objList = tempList;
        currentSize = objList.length;
    }

    public String toString() {
        String printString = "";
        for (T obj: objList){
            if(obj == null) {
                continue;
            }
            printString += (obj.toString() + "\n");
        }

        return printString;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void clear() {
        T tempObj = objList[0];
        objList = (T[]) Array.newInstance(tempObj.getClass(), 0);
        currentSize = 0;
    }

    public int indexOf(T o) {
        for(int i = 0; i < objList.length; i++) {
            if(objList[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && objList[currentIndex] != null;
            }

            @Override
            public T next() {
                return objList[currentIndex++];
            }
        };

        return it;
    }
}
