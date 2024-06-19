package es.cesguiro;

public class LambdaFunctions {

   // Without functional programming
   AddOperation addOperation = new AddOperation() {
      @Override
      public Integer add(Integer a, Integer b) {
         return a + b;
      }
   };

   // With functional programming
   AddOperation addOperationFunctional = (a, b) -> a + b;

   public Integer add(Integer a, Integer b, AddOperation addOperation) {
      return addOperation.add(a, b);
   }
}
