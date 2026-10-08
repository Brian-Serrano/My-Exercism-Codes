import { ElectronicDevice } from './lib.js';

export function isBoolean(value) {
  return typeof value === "boolean";
}

export function isNumber(value) {
  return (typeof value === "number" && Number.isFinite(value)) || typeof value === "bigint";
}

export function isObject(value) {
  return value !== null && typeof value === "object";
}

export function isNumericString(value) {
  return typeof value === "string" && value.length > 0 && !isNaN(Number(value));
}

export function isElectronic(object) {
  return object instanceof ElectronicDevice;
}

export function isNonEmptyArray(value) {
  return Array.isArray(value) && value.length > 0;
}

export function isEmptyArray(value) {
  return Array.isArray(value) && value.length == 0;
}

export function hasType(object) {
  return "type" in object;
}

export function assertHasId(object) {
  if ("id" in object)
    return undefined;
  else
    throw new Error();
}

export function hasIdProperty(object) {
  return Object.hasOwn(object, "id");
}

export function hasDefinedType(object) {
  return Object.hasOwn(object, "type") && object.type !== undefined;
}
