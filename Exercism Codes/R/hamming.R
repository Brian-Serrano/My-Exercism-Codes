hamming <- function(strand1, strand2) {
  if(nchar(strand1) != nchar(strand2)) stop()
  count <- 0
  for(i in 1:nchar(strand1)) {
    if(substring(strand1, i, i) != substring(strand2, i, i)) {
      count <- count + 1
    }
  }
  count
}
