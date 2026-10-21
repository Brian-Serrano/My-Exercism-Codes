//
// This is only a SKELETON file for the 'Promises' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const promisify = (callback) => {
  return (x) => new Promise((resolve, reject) => {
    callback(x, (error, data) => {
      if (error) {
        reject(error);
      }
      else {
        resolve(data);
      }
    });
  });
};

export const all = (array) => {
  if (array == undefined) {
    return Promise.resolve(undefined);
  }
  
  return Promise.all(array);
};

export const allSettled = (array) => {
  if (array == undefined) {
    return Promise.resolve(undefined);
  }
  
  return Promise.allSettled(array)
    .then(results => results.map(r => r.status === "fulfilled" ? r.value : r.reason));
};

export const race = (array) => {
  if (array == undefined) {
    return Promise.resolve(undefined);
  }

  if (array.length === 0) {
    return Promise.resolve([]);
  }
  
  return Promise.race(array);
};

export const any = (array) => {
  if (array == undefined) {
    return Promise.resolve(undefined);
  }
  
  if (array.length === 0) {
    return Promise.resolve([]);
  }

  return Promise.any(array).catch(error => {
    if (error instanceof AggregateError) {
      throw error.errors;
    }
    throw error;
  });
};
