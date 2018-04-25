package ali.javac;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TreeScanner;


public class JavaSourceVisitor extends TreeScanner {

    private String pkgName;

    @Override
    public Object visitCompilationUnit(CompilationUnitTree compilationUnitTree, Object o) {
        pkgName = compilationUnitTree.getPackageName().toString();
        System.out.println("pkgName="+pkgName);
        return super.visitCompilationUnit(compilationUnitTree, o);
    }
}
