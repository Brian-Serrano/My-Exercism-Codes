module hamming
  implicit none
contains
  function compute(strand1, strand2, distance)
      character(*) :: strand1, strand2
      integer :: distance, len1, len2, idx
      logical :: compute
      compute = .false.
      distance = 0
      len1 = len(strand1)
      len2 = len(strand2)
      if(len1 == len2) then
        compute = .true.
        do idx = 1, len1
          if (strand1(idx:idx) /= strand2(idx:idx)) then
            distance = distance + 1
          end if
        end do
      end if
  end function compute
end module hamming