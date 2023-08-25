function demo(fruit, weight, price) {
    let fruitType = fruit;
    let fruitWeight = weight;
    let pricePerKilo = price;

    let weightInKilos = fruitWeight / 1000;
    let totalSum = weightInKilos * pricePerKilo;
    console.log(`I need $${totalSum.toFixed(2)} to buy ${weightInKilos.toFixed(2)} kilograms ${fruitType}.`)
}
demo('orange', 2500, 1.80);