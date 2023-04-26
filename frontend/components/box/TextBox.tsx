//TextBox
import React from "react";
import * as style from "./style/TextBox";

type TextProps = {
  content: string;
  size: number;
  color: string;
};

const TextBox = ({ content, size, color }: TextProps) => {
  return (
    <style.Text size={size} color={color}>
      {content}
    </style.Text>
  );
};

TextBox.defaultProps = {
  size: 10,
  color: "#333333",
};

export default TextBox;
