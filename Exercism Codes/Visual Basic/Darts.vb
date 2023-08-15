Imports System

Public Module Darts
    Public Function Score(ByVal x As Double, ByVal y As Double) As Integer
        Dim distance As Double = Math.Sqrt(x * x + y * y)
        If distance > 10 Then
            Return 0
        ElseIf distance > 5 Then
            Return 1
        ElseIf distance > 1 Then
            Return 5
        Else
            Return 10
        End If
    End Function
End Module
