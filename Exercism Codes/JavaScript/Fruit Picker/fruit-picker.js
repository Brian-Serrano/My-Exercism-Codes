import { notify } from './notifier';
import { order } from './grocer';

export function onSuccess() {
  return notify({"message": "SUCCESS"});
}

export function onError() {
  return notify({"message": "ERROR"});
}

export function orderFromGrocer(query, onSuccessCallback, onErrorCallback) {
  return order(query, onSuccessCallback, onErrorCallback);
}

export function postOrder(variety, quantity) {
  return order({ variety, quantity }, onSuccess, onError);
}