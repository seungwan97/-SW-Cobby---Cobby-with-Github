import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/GithubBadge";

const GithubBadge = () => {
  return (
    <style.GithubBadgeWrapper>
      <TextBox
        size={20}
        content={"# GITHUB README BADGE"}
      />
      <style.CodeCopy>
        <style.ReadmeCode>
          <TextBox
            size={20}
            content={
              "https://cobby-play.com/baefrica/badge..."
            }
          />
        </style.ReadmeCode>
        <style.CopyBtn>
          <style.CopyBtnImg></style.CopyBtnImg>
        </style.CopyBtn>
      </style.CodeCopy>
    </style.GithubBadgeWrapper>
  );
};
export default GithubBadge;
