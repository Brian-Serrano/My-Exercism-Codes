using System;

class WeighingMachine
{
    public int Precision { get; }

    private double weight;
    public double Weight { 
        get { return weight; }
        set { weight = value < 0 ? throw new ArgumentOutOfRangeException() : value; }
    }

    public string DisplayWeight { 
        get { return $"{(Weight - TareAdjustment).ToString("F" + Precision)} kg"; }
    }

    public double TareAdjustment { get; set; } = 5;

    public WeighingMachine(int precision)
    {
        Precision = precision;
    }
}
