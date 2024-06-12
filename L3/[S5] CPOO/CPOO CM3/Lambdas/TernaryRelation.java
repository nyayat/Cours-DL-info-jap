package lambdaexemples;
//exemple de ma propre interface fonctionnelle

//pour représenter une relation ternaire sur un type T
//exemples d'utilisation dans la classe SecondOrder

@FunctionalInterface
public interface TernaryRelation<T> {
	Boolean test(T x, T y, T z);
}
