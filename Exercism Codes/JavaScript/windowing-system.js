function Size(width = 80, height = 60) {
  this.width = width;
  this.height = height;
}

Size.prototype.resize = function(newWidth, newHeight) {
  this.width = newWidth;
  this.height = newHeight;
}

function Position(x = 0, y = 0) {
  this.x = x;
  this.y = y;
}

Position.prototype.move = function(newX, newY) {
  this.x = newX;
  this.y = newY;
}

class ProgramWindow {
  constructor() {
    this.screenSize = new Size(800, 600);
    this.size = new Size();
    this.position = new Position();
  }
  resize(size) {
    this.size.width = size.width < 1 ? 1 : (size.width + this.position.x > this.screenSize.width ? this.screenSize.width - this.position.x : size.width);
    this.size.height = size.height < 1 ? 1 : (size.height + this.position.y > this.screenSize.height ? this.screenSize.height - this.position.y : size.height);
  }
  move(position) {
    this.position.x = position.x < 0 ? 0 : (position.x + this.size.width > this.screenSize.width ? this.screenSize.width - this.size.width : position.x);
    this.position.y = position.y < 0 ? 0 : (position.y + this.size.height > this.screenSize.height ? this.screenSize.height - this.size.height : position.y);
  }
}

function changeWindow(programWindow) {
  programWindow.resize(new Size(400, 300));
  programWindow.move(new Position(100, 150));
  return programWindow;
}

module.exports = { Size, Position, ProgramWindow, changeWindow };