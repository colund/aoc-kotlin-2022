class MyStack {
    // First element top of stack
    private val items: ArrayDeque<Char> = ArrayDeque()

    fun insertLast(char: Char) {
        items.add(char)
    }

    fun push(char: Char) {
        items.addFirst(char)
    }

    fun pushGroup(group: List<Char>) {
        group.reversed().forEach { items.addFirst(it) }
    }

    fun pop(): Char {
        return items.removeFirst()
    }

    fun popCount(count: Int): List<Char> {
        val result = mutableListOf<Char>()
        repeat(count) {
            result.add(items.removeFirst())
        }
        return result
    }

    override fun toString(): String {
        return "MyStack$items"
    }
}
