// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PAppRequest.proto

package com.argo.sdk.protobuf;

public interface PAppRequestParamOrBuilder
    extends com.google.protobuf.MessageOrBuilder {

  // required string name = 1;
  /**
   * <code>required string name = 1;</code>
   */
  boolean hasName();
  /**
   * <code>required string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <code>required string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  // optional int32 intValue = 2;
  /**
   * <code>optional int32 intValue = 2;</code>
   */
  boolean hasIntValue();
  /**
   * <code>optional int32 intValue = 2;</code>
   */
  int getIntValue();

  // optional string strValue = 3;
  /**
   * <code>optional string strValue = 3;</code>
   */
  boolean hasStrValue();
  /**
   * <code>optional string strValue = 3;</code>
   */
  java.lang.String getStrValue();
  /**
   * <code>optional string strValue = 3;</code>
   */
  com.google.protobuf.ByteString
      getStrValueBytes();

  // optional int64 longValue = 4;
  /**
   * <code>optional int64 longValue = 4;</code>
   */
  boolean hasLongValue();
  /**
   * <code>optional int64 longValue = 4;</code>
   */
  long getLongValue();

  // optional float floatValue = 5;
  /**
   * <code>optional float floatValue = 5;</code>
   */
  boolean hasFloatValue();
  /**
   * <code>optional float floatValue = 5;</code>
   */
  float getFloatValue();

  // optional bool boolValue = 6;
  /**
   * <code>optional bool boolValue = 6;</code>
   */
  boolean hasBoolValue();
  /**
   * <code>optional bool boolValue = 6;</code>
   */
  boolean getBoolValue();

  // optional bytes bytesValue = 7;
  /**
   * <code>optional bytes bytesValue = 7;</code>
   */
  boolean hasBytesValue();
  /**
   * <code>optional bytes bytesValue = 7;</code>
   */
  com.google.protobuf.ByteString getBytesValue();
}
