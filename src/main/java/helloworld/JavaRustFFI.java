package helloworld;

import java.io.File;

import jnr.ffi.LibraryLoader;

import static java.lang.System.mapLibraryName;


/**
 * JavaRustFFI class
 *
 * @author Suresh G(@sur3shg)
 * @version 1.0
 */
public class JavaRustFFI {

    public static interface RustLib {
        int double_input(int i);
    }

    public static String getLibraryPath(String dylib) {
        File f = new File(JavaRustFFI.class.getClassLoader().getResource(mapLibraryName(dylib)).getFile());
        return f.getParent();
    }

    public static void main(String[] args) {
        String dylib = "double_input";
        System.setProperty("jnr.ffi.library.path", getLibraryPath(dylib));

        RustLib rlib = LibraryLoader.create(RustLib.class).load(dylib);
        int r = rlib.double_input(20);

        System.out.println("Result from rust double_input:  " + r);
    }
}
