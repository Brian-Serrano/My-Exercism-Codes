export class TranslationService {

  constructor(api) {
    this.api = api;
  }
  
  free(text) {
    return this.api.fetch(text).then(r => r.translation);
  }

  batch(texts) {
    return texts.length == 0 ? Promise.reject(new BatchIsEmpty()) : Promise.all(texts.map(t => this.free(t)));
  }

  request(text) {
    const p = () => new Promise((resolve, reject) => this.api.request(text, res => res ? reject(res) : resolve()));
    return p().catch(p).catch(p);
  }

  premium(text, minimumQuality) {
    return this.api.fetch(text).catch(() => this.request(text).then(() => this.api.fetch(text))).then(res => {
      if(res.quality < minimumQuality) throw new QualityThresholdNotMet();
      return res.translation;
    });
  }
}

export class QualityThresholdNotMet extends Error {

  constructor(text) {
    super(
      `
The translation of ${text} does not meet the requested quality threshold.
    `.trim(),
    );

    this.text = text;
  }
}

export class BatchIsEmpty extends Error {
  constructor() {
    super(
      `
Requested a batch translation, but there are no texts in the batch.
    `.trim(),
    );
  }
}
