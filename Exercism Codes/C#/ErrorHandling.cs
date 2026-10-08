public static class ErrorHandling
{
    public static void HandleErrorByThrowingException()
    {
        throw new Exception();
    }

    public static int? HandleErrorByReturningNullableType(string input)
    {
        try
        {
            return int.Parse(input);

        }
        catch (FormatException)
        {
            return null;
        }
    }

    public static bool HandleErrorWithOutParam(string input, out int result)
    {
        try
        {
            result = int.Parse(input);
            return true;

        }
        catch (FormatException)
        {
            result = 0;
            return false;
        }
    }

    public static void DisposableResourcesAreDisposedWhenExceptionIsThrown(IDisposable disposableObject)
    {
        disposableObject.Dispose();

        throw new Exception();
    }
}
