module SavingsAccount
  def self.interest_rate(balance)
    if balance >= 0 && balance < 1000
      return 0.5
    elsif balance >= 1000 && balance < 5000
      return 1.621
    elsif balance >= 5000
      return 2.475
    else
      return 3.213
    end
  end

  def self.annual_balance_update(balance)
    return balance + (balance * (interest_rate(balance) / 100))
  end

  def self.years_before_desired_balance(current_balance, desired_balance)
    days = 0
    while current_balance < desired_balance
      current_balance = annual_balance_update(current_balance)
      days += 1
    end
    return days
  end
end
