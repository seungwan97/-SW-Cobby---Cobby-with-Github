//TextBox
import React from "react";
import * as style from "./style/TextBox";

type TextProps = {
  content: string;
  size: number;
};

const TextBox = ({ content, size }: TextProps) => {
  return <style.Text size={size}>{content}</style.Text>;
};

TextBox.defaultProps = {
  size: 10,
};

export default TextBox;
