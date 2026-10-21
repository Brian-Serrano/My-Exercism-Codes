public static class Prism
{
    public readonly record struct LaserInfo(double X, double Y, double Angle);

    public readonly record struct PrismInfo(int Id, double X, double Y, double Angle);

    public static int[] FindSequence(LaserInfo laser, PrismInfo[] prisms)
    {
        double cx = laser.X;
        double cy = laser.Y;
        double cAngle = ToPositiveAngle(laser.Angle);
        List<int> result = new List<int>();

        while (true)
        {
            List<PrismInfo> paths = new List<PrismInfo>();
            foreach (PrismInfo prism in prisms)
            {
                bool notSameLocation = !(prism.Y - cy == 0 && prism.X - cx == 0);
                double rAngle = Math.Atan2(prism.Y - cy, prism.X - cx);
                double angle = ToPositiveAngle(Double.RadiansToDegrees(rAngle));
                double difference = Math.Abs(angle - cAngle);
                bool angleEqual = Math.Min(difference, 360.0 - difference) <= 0.005;
                if (notSameLocation && angleEqual)
                    paths.Add(prism);
            }

            if (paths.Count == 0)
                break;

            if (paths.Count > 1)
            {
                PrismInfo nearestPrism = paths.MinBy(p => GetMin(p, cx, cy));

                result.Add(nearestPrism.Id);
                cx = nearestPrism.X;
                cy = nearestPrism.Y;
                cAngle = ToPositiveAngle(cAngle + nearestPrism.Angle);
            }
            else
            {
                result.Add(paths.First().Id);
                cx = paths.First().X;
                cy = paths.First().Y;
                cAngle = ToPositiveAngle(cAngle + paths.First().Angle);
            }
        }

        return result.ToArray();
    }

    private static double ToPositiveAngle(double angle)
    {
        double value = angle - Math.Floor(angle / 360.0) * 360.0;
        return Math.Round(value * 1000.0) / 1000.0;
    }

    private static double GetMin(PrismInfo p, double currentX, double currentY)
    {
        double dx = p.X - currentX;
        double dy = p.Y - currentY;
        return dx * dx + dy * dy;
    }
}
