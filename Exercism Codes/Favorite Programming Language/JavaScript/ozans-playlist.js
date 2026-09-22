export function removeDuplicates(playlist) {
  return [...new Set(playlist)];
}

export function hasTrack(playlist, track) {
  return new Set(playlist).has(track);
}

export function addTrack(playlist, track) {
  return [...new Set(playlist).add(track)];
}

export function deleteTrack(playlist, track) {
  const set = new Set(playlist);
  set.delete(track);
  return [...set];
}

export function listArtists(playlist) {
  return [...new Set(playlist.map(p => p.split(' - ')[1]))];
}
