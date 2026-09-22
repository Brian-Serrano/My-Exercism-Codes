Imports System.Runtime.CompilerServices

Module AccumulateExtensions
    <Extension()>
    Public Function Accumulate(Of T)(lst As IEnumerable(Of T), func As Func(Of T, T)) As IEnumerable(Of T)
        Return lst.Select(Function(l) func(l))
    End Function
End Module