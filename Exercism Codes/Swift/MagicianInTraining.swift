func getCard(at index: Int, from stack: [Int]) -> Int {
  return stack[index]
}

func setCard(at index: Int, in stack: [Int], to newCard: Int) -> [Int] {
  var new_stack: [Int] = stack
  guard new_stack.indices.contains(index) else { return stack }
  new_stack[index] = newCard
  return new_stack
}

func insert(_ newCard: Int, atTopOf stack: [Int]) -> [Int] {
  var new_stack: [Int] = stack
  new_stack.append(newCard)
  return new_stack
}

func removeCard(at index: Int, from stack: [Int]) -> [Int] {
  var new_stack: [Int] = stack
  guard new_stack.indices.contains(index) else { return stack }
  new_stack.remove(at: index)
  return new_stack
}

func removeTopCard(_ stack: [Int]) -> [Int] {
  var new_stack: [Int] = stack
  guard !new_stack.isEmpty else { return stack }
  new_stack.removeLast()
  return new_stack
}

func insert(_ newCard: Int, atBottomOf stack: [Int]) -> [Int] {
  var new_stack: [Int] = stack
  new_stack.insert(newCard, at: 0)
  return new_stack
}

func removeBottomCard(_ stack: [Int]) -> [Int] {
  var new_stack: [Int] = stack
  guard !new_stack.isEmpty else { return stack }
  new_stack.removeFirst()
  return new_stack
}

func checkSizeOfStack(_ stack: [Int], _ size: Int) -> Bool {
  return stack.count == size
}

func evenCardCount(_ stack: [Int]) -> Int {
  stack.filter({ $0 % 2 == 0 }).count
}
