score <- function(x, y) {
  dst <- sqrt(x * x + y * y)
  if(dst > 10) {
    return(0)
  }
  else if(dst > 5) {
    return(1)
  }
  else if(dst > 1) {
    return(5)
  }
  else {
    return(10)
  }
}
