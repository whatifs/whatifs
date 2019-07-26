# What If We exclusively use property based testing

## Proposition

  * Property based testing is an effective way of documenting contracts (pre and 
  post conditions to functions), or other laws / properties in your codebase.
  * Property based testing facilitates the generation of specific test cases and 
  thus speeds up the speed with which you write tests.
  * Property based testing reduces duplication, but providing a framework for 
  re-use of specific data values.
  * Property based testing increases the coverage of your code and thus helps 
  detect harder to find bugs.

## Experiment Videos

### From unit to property
Convert an existing unit test, to a property based test.

### From property to unit.
Use the generator framework to run a specific test.

### Refine the input types.
Use type refinement to ensure the compiler catches illegal values instead of relying on unit testing.

### Refine dependent input.
Use type refinements and path dependent types to ensure the compiler catches illegal values instead of relying on unit testing.