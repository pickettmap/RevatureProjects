package utilTest;

import org.reform.model.Person;
import org.reform.model.Pet;
import org.reform.util.TypeMappingUtil;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TypeMappingUtilTest {

    //TODO: Clean this test up!!
    @Test
    public void isPrimitiveTypeTest(){
        Integer i = 1;
        Pet p = new Pet("Duke","dog");
        String s = "yeeted";

        Person test = new Person("mik",22,p);
        Field[] field = test.getClass().getDeclaredFields();

        assertTrue(TypeMappingUtil.isPrimitiveType(s));

        //Checking primitive
        assertTrue(TypeMappingUtil.isPrimitiveType(false));

        //Checking wrapper class
        assertTrue(TypeMappingUtil.isPrimitiveType(i));
//        System.out.println("Printing pet class: " + p.getClass());

        //Checking custom class
        assertFalse(TypeMappingUtil.isPrimitiveType(p.getClass()));
//        for(Field f : field) {
//            System.out.println("Printing pet type: " + f.getType());
//            System.out.println("Printing type bool: " + TypeMappingUtil.isPrimitiveType(f.getType()));
////            assertEquals(f.getType(), String.class);
//        }

    }

    @Test
    public void isHashMapCollectionTest() {
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        assertTrue(TypeMappingUtil.isCollection(m.getClass()));
    }

    @Test
    public void isArrayListCollectionTest() {
        ArrayList<Integer> arr = new ArrayList<>();
        assertTrue(TypeMappingUtil.isCollection(arr.getClass()));
    }

    /*
    I can check if the field is a collection or an array
    I can check if the field is a custom class using @entity
    I can check if the field is an array of custom classes

    I cannot check if the field is a collection of custom classes
     */
    @Test
    public void anotherTest() {
        Pet p = new Pet("yue", "bird");

        ArrayList<Pet> pee = new ArrayList<>();
        pee.add(p);

        Pet[] piss = new Pet[1];
        piss[0] = p;

        System.out.println(piss.getClass().getComponentType());
    }
}
