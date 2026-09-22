class AssemblyLine
  def initialize(speed)
    @speed = speed
  end

  def production_rate_per_hour
    if @speed >= 1 && @speed <= 4
      return @speed * 221 * 1
    elsif @speed >= 5 && @speed <= 8
      return @speed * 221 * 0.9
    elsif @speed == 9
      return @speed * 221 * 0.8
    else
      return @speed * 221 * 0.77
    end
  end

  def working_items_per_minute
    (production_rate_per_hour / 60).floor
  end
end
