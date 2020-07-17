package controllers;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class pyTest {
    public static void main(String [] abhi) throws FileNotFoundException, ScriptException {
       /*
        //StringWriter writer = new StringWriter();
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptContext context = new SimpleScriptContext();
        //context.setWriter(writer);
        ScriptEngine engine = manager.getEngineByName("python");
        engine.eval(new FileReader("C:\\Users\\Abhi\\Desktop\\examples\\pytestFile.py"), context);
        //System.out.println(writer.toString());
        */



        System.out.println("Abhishek hello");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptContext context = new SimpleScriptContext();
        ScriptEngine engine = manager.getEngineByName("python");
        engine.eval(new FileReader("C:\\Users\\Abhi\\Desktop\\examples\\pytestFile.py"), context);
        //engine.eval(new FileReader("C:\\Users\\Abhi\\Desktop\\examples\\mainProgram.py"), context);

        /*
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            //pyInterp.execfile("C:\\Users\\Abhi\\Desktop\\examples\\mainProgram.py");
            pyInterp.execfile("C:\\Users\\Abhi\\Desktop\\examples\\pytestFile");

        }
         */
    }

}
