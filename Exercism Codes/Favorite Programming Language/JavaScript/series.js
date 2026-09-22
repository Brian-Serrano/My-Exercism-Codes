export class Series {
  constructor(series = "") {
    this.series = series;
  }

  slices(sliceLength) {
    if (this.series.length === 0) {
      throw new Error('series cannot be empty');
    }
    if (sliceLength === 0) {
      throw new Error('slice length cannot be zero');
    }
    if (sliceLength < 0) {
      throw new Error('slice length cannot be negative');
    }
    if (sliceLength > this.series.length) {
      throw new Error('slice length cannot be greater than series length');
    }

    const result = [];
    for (let i = 0; i <= this.series.length - sliceLength; i++) {
      result.push(this.series
        .substring(i, i + sliceLength)
        .split("").map(x => parseInt(x)));
    }
    return result;
  }
}