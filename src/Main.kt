import kotlinx.coroutines.delay

suspend fun main() {
	selectedMenu()
}

sealed class Coffee(val name: String, var sugar: Int, var volume: Double) {
	class Americano : Coffee("Americano", sugar = 2, volume = 0.2)
	class Cappuccino(var milk: String) : Coffee("Cappuccino", sugar = 2, volume = 0.2)
	class Latte : Coffee("Latte", sugar = 2, volume = 0.2)
}

suspend fun selectedMenu() {
	val americano = Coffee.Americano()
	val cappuccino = Coffee.Cappuccino("milk")
	val latte = Coffee.Latte()
	
	println(
		"Выберите кофе\n" +
				"1. Americano.\n" +
				"2. Cappuccino.\n" +
				"3. Latte\n" +
				"Введите номер"
	)
	val coffee = readln()
	if (coffee !in ("1".."3")) {
		println("Ошибка, не корректный выбор кофе")
		return
	}
	
	println("Выберите количество сахара от 2 до 5")
	val sugar = readln()
	if (sugar !in ("2".."5")) {
		println("Ошибка, не корректный выбор сахара")
		return
	}
	
	println(
		"Выберети необходимый объем стаканчика\n" +
				"0.2\n" +
				"0.5"
	)
	val volume = readln()
	if (volume != "0.2" && volume != "0.5") {
		println("Ошибка, не корректный выбор объема стаканчика")
		return
	}
	
	when (coffee) {
		"1" -> {
			americano.sugar = sugar.toInt()
			americano.volume = volume.toDouble()
			selectMenu(americano)
		}
		
		"2" -> {
			cappuccino.sugar = sugar.toInt()
			cappuccino.volume = volume.toDouble()
			cappuccino.milk = "milk"
			selectMenu(cappuccino)
		}
		
		"3" -> {
			latte.sugar = sugar.toInt()
			latte.volume = volume.toDouble()
			selectMenu(latte)
		}
	}
	
}

suspend fun selectMenu(coffee: Coffee) {
	for (i in 10..100 step 10) {
		print("$i% ")
		delay(500L)
	}
	println()
	if (coffee is Coffee.Cappuccino)
		println("Ваш кофе готов: ${coffee.name}, сахар: ${coffee.sugar}, объем: ${coffee.volume}, с молоком.")
	else println("Ваш кофе готов: ${coffee.name}, сахар: ${coffee.sugar}, объем: ${coffee.volume}")
}