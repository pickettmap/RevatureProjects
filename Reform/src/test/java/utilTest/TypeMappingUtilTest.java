package utilTest;

import org.reform.model.Person;
import org.reform.model.Pet;
import org.reform.util.TypeMappingUtil;
import org.junit.Test;

import java.lang.reflect.Field;

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
}
