//
// This is only a SKELETON file for the 'Tournament' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const tournamentTally = (string) => {
  const data = string == "" ? [] : string.split("\n").map(x => x.split(";"));
  const players = [];
  for (const d of data) {
    for (let i = 0; i < 2; i++) {
      const status = i == 0 ? d[2] : reverse(d[2]);
      const playerIdx = players.map(p => p.name).indexOf(d[i]);
      if (playerIdx < 0)
        players.push(new Player(d[i], status));
      else
        players[playerIdx].update(status);
    }
  }
  const result = players.toSorted((a, b) => a.points == b.points ? a.name.localeCompare(b.name) : b.points - a.points);
  let lines = ["Team                           | MP |  W |  D |  L |  P"];
  for (const p of result) {
    lines.push(`${p.name}${" ".repeat(31 - p.name.length)}| ${ps(p.played)} | ${ps(p.win)} | ${ps(p.draw)} | ${ps(p.loss)} | ${ps(p.points)}`);
  }
  return lines.join("\n");
};

const ps = (p) => p.toString().padStart(2, " ");

const reverse = (status) => {
  return status == "draw" ? "draw" : (status == "win" ? "loss" : "win");
};


class Player {
  constructor(name, value) {
    this.name = name;
    this.win = 0;
    this.loss = 0;
    this.draw = 0;
    this.played = 0;
    this.points = 0;
    this.update(value);
  }

  update(value) {
    if (value == "win")
      this.win++;
    if (value == "draw")
      this.draw++;
    if (value == "loss")
      this.loss++;
    this.played = this.win + this.draw + this.loss;
    this.points = this.win * 3 + this.draw;
  }
}