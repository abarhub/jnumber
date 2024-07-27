package org.testnumber.jnumber.service;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.ISymbol;
import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.math.MathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.testnumber.jnumber.math.Addition;
import org.testnumber.jnumber.math.Multiplication;

import java.util.*;

@Service
public class MathService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public void construit(Map<Integer, Integer> map) {

        LOGGER.info("construit");

        List<Addition> liste1 = new ArrayList<Addition>();

        List<Integer> liste2 = new ArrayList<>(map.keySet());
        Collections.sort(liste2);
        for (int i = 0; i < liste2.size(); i++) {
            int x = liste2.get(i);
            int y = map.get(x);
            LOGGER.info("x=" + x + ",y=" + y);

            List<Multiplication> liste3 = new ArrayList<Multiplication>();

            for (int j = 0; i < liste2.size(); j++) {
                int k = liste2.get(j);
                if (x != k) {

                }
            }
        }

    }

//    public static IExpr lagrangePolynomial(double[] xValues, double[] yValues) {
//        ExprEvaluator util = new ExprEvaluator();
//        IAST polynomial = F.Plus();
//
//        int n = xValues.length;
//        for (int i = 0; i < n; i++) {
//            IAST term = F.Times();
//            term.add(F.num(yValues[i]));
//
//            for (int j = 0; j < n; j++) {
//                if (j != i) {
//                    IAST fraction = F.Divide(
//                            F.Subtract(F.x, F.num(xValues[j])),
//                            F.Subtract(F.num(xValues[i]), F.num(xValues[j]))
//                    );
//                    term.add(fraction);
//                }
//            }
//            polynomial.add(term);
//        }
//
//        return util.evaluate(polynomial);
//    }


    public void test() {
        try {
            ExprEvaluator util = new ExprEvaluator(false, (short) 100);

            // Convert an expression to the internal Java form:
            // Note: single character identifiers are case sensitive
            // (the "D()" function identifier must be written as upper case
            // character)
            String javaForm = util.toJavaForm("D(sin(x)*cos(x),x)");
            // prints: D(Times(Sin(x),Cos(x)),x)
            System.out.println("Out[1]: " + javaForm.toString());

            // Use the Java form to create an expression with F.* static
            // methods:
            ISymbol x = F.Dummy("x");
            IAST function = F.D(F.Times(F.Sin(x), F.Cos(x)), x);
            IExpr result = util.eval(function);
            // print: Cos(x)^2-Sin(x)^2
            System.out.println("Out[2]: " + result.toString());

            // Note "diff" is an alias for the "D" function
            result = util.eval("diff(sin(x)*cos(x),x)");
            // print: Cos(x)^2-Sin(x)^2
            System.out.println("Out[3]: " + result.toString());

            // evaluate the last result (% or $ans contains "last answer")
            result = util.eval("%+cos(x)^2");
            // print: 2*Cos(x)^2-Sin(x)^2
            System.out.println("Out[4]: " + result.toString());

            // evaluate an Integrate[] expression
            result = util.eval("integrate(sin(x)^5,x)");
            // print: 2/3*Cos(x)^3-1/5*Cos(x)^5-Cos(x)
            System.out.println("Out[5]: " + result.toString());

            // set the value of a variable "a" to 10
            // Note: in server mode the variable name must have a preceding '$'
            // character
            result = util.eval("a=10");
            // print: 10
            System.out.println("Out[6]: " + result.toString());

            // do a calculation with variable "a"
            result = util.eval("a*3+b");
            // print: 30+b
            System.out.println("Out[7]: " + result.toString());

            // Do a calculation in "numeric mode" with the N() function
            // Note: single character identifiers are case sensistive
            // (the "N()" function identifier must be written as upper case
            // character)
            result = util.eval("N(sinh(5))");
            // print: 74.20321057778875
            System.out.println("Out[8]: " + result.toString());

            // define a function with a recursive factorial function definition.
            // Note: fac(0) is the stop condition.
            result = util.eval("fac(x_Integer):=x*fac(x-1);fac(0)=1");
            // now calculate factorial of 10:
            result = util.eval("fac(10)");
            // print: 3628800
            System.out.println("Out[9]: " + result.toString());

            function =
                    F.Function(F.Divide(F.Gamma(F.Plus(F.C1, F.Slot1)), F.Gamma(F.Plus(F.C1, F.Slot2))));
            // eval function ( Gamma(1+#1)/Gamma(1+#2) ) & [23,20]
            result = util.evalFunction(function, "23", "20");
            // print: 10626
            System.out.println("Out[10]: " + result.toString());
        } catch (SyntaxError e) {
            // catch Symja parser errors here
            System.out.println(e.getMessage());
        } catch (MathException me) {
            // catch Symja math errors here
            System.out.println(me.getMessage());
        } catch (final Exception ex) {
            System.out.println(ex.getMessage());
        } catch (final StackOverflowError soe) {
            System.out.println(soe.getMessage());
        } catch (final OutOfMemoryError oome) {
            System.out.println(oome.getMessage());
        }
    }

    public void test2() {
        ExprEvaluator util = new ExprEvaluator();

        // Définir les points d'interpolation
        double[][] points = {{1, 1}, {2, 4}, {3, 9}};

        // Créer une liste de points pour Symja
        IExpr pointsList = F.List();
        for (double[] point : points) {
            if (pointsList.isEmpty()) {
                pointsList = F.List(F.List(F.num(point[0]), F.num(point[1])));
            } else {
                pointsList = F.List(pointsList, F.List(F.num(point[0]), F.num(point[1])));
            }
        }

        // Calculer le polynôme d'interpolation de Lagrange
        String command = "InterpolatingPolynomial(" + pointsList.toString() + ", x)";
        IExpr result = util.eval(command);

        System.out.println("Polynôme d'interpolation de Lagrange : " + result.toString());

        // Évaluer le polynôme pour une valeur spécifique (par exemple, x = 2.5)
        IExpr evaluatedResult = util.eval(result.toString() + " /. x -> 2.5");
        System.out.println("Valeur du polynôme à x = 2.5 : " + evaluatedResult.toString());
    }

    public void test4() {
        ExprEvaluator util = new ExprEvaluator();

        // Définir les points d'interpolation
        double[][] points = {{1, 1}, {2, 2}, {3, 3}};

        // Créer une liste de points pour Symja
        IExpr pointsList = F.List();
        String s="";
        for (double[] point : points) {
            if (pointsList.isEmpty()) {
                pointsList = F.List(F.List(F.num(point[0]), F.num(point[1])));
            } else {

                pointsList = F.List(pointsList, F.List(F.num(point[0]), F.num(point[1])));
//                pointsList.add(F.List(F.num(point[0]), F.num(point[1])));
//                pointsList. = F.operatorForm1Append(pointsList, F.List(F.num(point[0]), F.num(point[1])));
            }
            if(!s.isEmpty()){
                s+=",";
            }
            s+="{"+point[0]+","+point[1]+"}";
        }

        LOGGER.info("Liste des points : {}", pointsList);
        LOGGER.info("Liste des points2 : {}", s);

        // Calculer le polynôme d'interpolation de Lagrange
        //String command = "InterpolatingPolynomial(" + pointsList.toString() + ", x)";
        String command = "InterpolatingPolynomial({" + s + "}, x)";
        IExpr result = util.eval(command);

        LOGGER.info("Polynôme d'interpolation de Lagrange : {}", result);

        // Évaluer le polynôme pour une valeur spécifique (par exemple, x = 2.5)
        IExpr evaluatedResult = util.eval(result.toString() + " /. x -> 2.5");
        LOGGER.info("Valeur du polynôme à x = 2.5 : {}", evaluatedResult);

        IExpr result2 = F.evalSimplify(result);
        LOGGER.info("Polynôme d'interpolation de Lagrange simplifie : {}", result2);
    }

    public void test5(){

        // Définir les points d'interpolation
        List<List<Integer>> points = List.of(List.of(1, 1), List.of(2, 2), List.of(3, 3));
        var res=calculInterpolation(points);

        LOGGER.info("Polynôme d'interpolation de Lagrange simplifie : {}", res);

        listeFonctions();

    }

    public List<List<List<Integer>>> listeFonctions() {
        List<List<List<Integer>>> listeResultat=new ArrayList<>();

        int N=3;

//        Map<Integer,Integer> map=new TreeMap<>();
//        for(int i=1;i<=3;i++){
//            for(int j=1;j<=N;j++){
//                if()
//            }
//        }
        List<Integer> function = new ArrayList<>(List.of(1, 2, 3));
        enumerateBijectiveFunctions(function, 0);

        return listeResultat;
    }

    private static void enumerateBijectiveFunctions(List<Integer> function, int start) {
        if (start == function.size()) {
            printFunction(function);
            return;
        }

        for (int i = start; i < function.size(); i++) {
            swap(function, start, i);
            enumerateBijectiveFunctions(function, start + 1);
            swap(function, start, i);  // backtrack
        }
    }

    private static void swap(List<Integer> array, int i, int j) {
        int temp = array.get(i);
        array.set(i,array.get(j));
        array.set(j, temp);
    }

    private static void printFunction(List<Integer> function) {
        System.out.print("f: ");
        for (int i = 0; i < function.size(); i++) {
            System.out.print((i + 1) + " -> " + function.get(i));
            if (i < function.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public IExpr calculInterpolation(List<List<Integer>> points){

        ExprEvaluator util = new ExprEvaluator();

        // Créer une liste de points pour Symja
//        IExpr pointsList = F.List();
        String s="";
        for (List<Integer> point : points) {
            Assert.isTrue(point.size()==2," la taille n'est pas bonne");
//            if (pointsList.isEmpty()) {
//                pointsList = F.List(F.List(F.num(point[0]), F.num(point[1])));
//            } else {
//
//                pointsList = F.List(pointsList, F.List(F.num(point[0]), F.num(point[1])));
////                pointsList.add(F.List(F.num(point[0]), F.num(point[1])));
////                pointsList. = F.operatorForm1Append(pointsList, F.List(F.num(point[0]), F.num(point[1])));
//            }
            if(!s.isEmpty()){
                s+=",";
            }
            s+="{"+point.get(0)+","+point.get(1)+"}";
        }

//        LOGGER.info("Liste des points : {}", pointsList);
        LOGGER.info("Liste des points : {}", s);

        // Calculer le polynôme d'interpolation de Lagrange
        //String command = "InterpolatingPolynomial(" + pointsList.toString() + ", x)";
        String command = "InterpolatingPolynomial({" + s + "}, x)";
        IExpr result = util.eval(command);

        LOGGER.info("Polynôme d'interpolation de Lagrange : {}", result);

        // Évaluer le polynôme pour une valeur spécifique (par exemple, x = 2.5)
//        IExpr evaluatedResult = util.eval(result.toString() + " /. x -> 2.5");
//        LOGGER.info("Valeur du polynôme à x = 2.5 : {}", evaluatedResult);

        IExpr result2 = F.evalSimplify(result);

        return result2;
    }

}
