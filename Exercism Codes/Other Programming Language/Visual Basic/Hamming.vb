Public Module Hamming
    Public Function Distance(ByVal firstStrand As String, ByVal secondStrand As String) As Integer
        Dim count As Integer = 0
        If firstStrand.Length <> secondStrand.Length Then
            Throw New ArgumentException()
        End If
        For idx As Integer = 0 To firstStrand.Length - 1
            If firstStrand.Chars(idx) <> secondStrand.Chars(idx) Then
                count += 1
            End If
        Next
        Return count
    End Function
End Module