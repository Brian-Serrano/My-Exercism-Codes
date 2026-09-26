export const encode = (msg, rails) =>
  createFence(msg.length, rails).map((i) => msg[i]).join("");
export const decode = (msg, rails) => {
  const fence = createFence(msg.length, rails);
  return [...Array(msg.length).keys()].map(i => msg[fence.indexOf(i)]).join("");
};
const createFence = (length, rails) => {
  const amplitude = 2 * rails - 2;
  return [...Array(rails).keys()]
    .flatMap(x => [...Array(length).keys()]
      .filter(y => y % amplitude === x || y % amplitude === amplitude - x
      )
  );
}