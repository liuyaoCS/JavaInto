package ali.javac;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javax.tools.JavaFileObject;

/**
 * Created by liuyao-s on 2018/4/23.
 */

public class Test {
    private static String source = "F:\\as\\project-practise\\JavaInto\\javalib\\src\\main\\java\\ali\\javac\\Input.java";
    public static void main(String[] args){
//        directCompile();
//        genAST(source);
        List<JCTree.JCCompilationUnit> trees = genASTWithSymbols(source);
        processAST(trees);
    }

    private static void directCompile() {

        com.sun.tools.javac.main.Main compiler = new com.sun.tools.javac.main.Main("javac");
        boolean ret= compiler.compile(new String[]{source}).isOK();
        System.out.println("compile result is "+ret);
    }
//    private static void genAST(String filePath) {
//        Context context = new Context();
//        JavacTool jcTool = new JavacTool();
//        JavacFileManager jcFileManager = new JavacFileManager(context, true, Charset.defaultCharset());
//
//        JavaSourceVisitor jsv = new JavaSourceVisitor();
//        Iterable<? extends JavaFileObject> javaFiles = jcFileManager.getJavaFileObjects(filePath);
//        JavaCompiler.CompilationTask cTask = jcTool.getTask(null, jcFileManager, null, null, null, javaFiles);
//        JavacTask jcTask = (JavacTask) cTask;
//
//        try {
//            Iterable<? extends CompilationUnitTree> codeResult = jcTask.parse();
//            for (CompilationUnitTree codeTree : codeResult) {
//                JCTree.JCCompilationUnit ast = (JCTree.JCCompilationUnit) codeTree;
//                List<JCTree> defs = ast.defs;
//                for(JCTree tree:defs){
//                    if(tree instanceof JCTree.JCImport){
//                        boolean isStatic=((JCTree.JCImport) tree).staticImport;
//                        System.out.println("staticImport="+isStatic);
//
//                    }else if(tree instanceof  JCTree.JCClassDecl){
//                        String name = ((JCTree.JCClassDecl) tree).name.toString();
//                        System.out.println("name="+name);
//                    }
//                }
//                ast.accept(jsv, null);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    private static List<JCTree.JCCompilationUnit> genASTWithSymbols(String filePath) {
        Context context = new Context();
        JavacFileManager jcFileManager = new JavacFileManager(context, true, Charset.defaultCharset());
        JavaCompiler comp = JavaCompiler.instance(context);

        List<JavaFileObject> fileObjects = List.nil();
        for(JavaFileObject jfo:jcFileManager.getJavaFileObjects(filePath)){
            fileObjects = fileObjects.prepend(jfo);
        }

        List<JCTree.JCCompilationUnit> trees = comp.parseFiles(fileObjects); //[step 1] gen ast
//        for(JCTree jcTree:trees){
//            System.out.println(jcTree.toString());
//        }
        trees = comp.enterTrees(trees);                                    //[step 2] add to symbol table
        comp = comp.processAnnotations(trees);                             //[step 3] process annotation
        Queue<Env<AttrContext>> queues= comp.attribute(comp.todo);         //[step 4] finish symbol table
//        for(Env<AttrContext> attrContextEnv:queues){
//            System.out.println(attrContextEnv.toString());
//        }
        return  trees;
    }
    private static void processAST(List<JCTree.JCCompilationUnit> trees){
        Set<String> imports = new HashSet<>();
        Set<String> requiredImports = new HashSet<>();

        JCTree.JCCompilationUnit unit = trees.get(0);
        List<JCTree> jtrees=unit.defs;
        for(JCTree jcTree:jtrees){
            if(jcTree instanceof JCTree.JCClassDecl){
                JCTree.JCClassDecl tmp = (JCTree.JCClassDecl) jcTree;
                List<JCTree> classTrees = tmp.defs;
                for(JCTree item:classTrees){
                    if(item instanceof JCTree.JCMethodDecl){
                        JCTree.JCMethodDecl methodTree = (JCTree.JCMethodDecl) item;

                        List<JCTree.JCAnnotation> annotations = methodTree.mods.annotations;
                        for(JCTree.JCAnnotation annotation:annotations){
                            System.out.println("annotation type="+annotation);
                        }

                        List<JCTree.JCStatement> stats = methodTree.body.stats;
                        for(JCTree.JCStatement jcStatement:stats){
                            if(jcStatement instanceof JCTree.JCVariableDecl){
                                Symbol.TypeSymbol dts = ((JCTree.JCVariableDecl) jcStatement).vartype.type.tsym;
                                System.out.println("dec type="+dts.toString());
                                if(imports.contains(dts.toString())){
                                    requiredImports.add(dts.toString());
                                }

                                Symbol.TypeSymbol ts = ((JCTree.JCVariableDecl) jcStatement).init.type.tsym;
                                System.out.println("init type="+ts.toString());
                                if(imports.contains(ts.toString())){
                                    requiredImports.add(ts.toString());
                                }
                            }
                        }
                    }
                }
            }else if(jcTree instanceof JCTree.JCImport){
                JCTree.JCImport tmp = (JCTree.JCImport) jcTree;
                imports.add(tmp.qualid.toString());
            }
        }

        for(String importItem:requiredImports){
            System.out.println("required import :"+importItem);
        }
    }

}
