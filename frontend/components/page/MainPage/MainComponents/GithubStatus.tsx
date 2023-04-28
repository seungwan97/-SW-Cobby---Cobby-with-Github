import * as style from "@/components/page/MainPage/MainComponents/style/GithubStatus";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import TextBox from "@/components/common/TextBox/TextBox";

const GithubStatus = () => {
  return (
    <style.StatusContainer>
      <style.StatusBar>
        <style.StatusBox1>
          <style.MinusIcon />
        </style.StatusBox1>
        <style.StatusBox2>
          <style.SquareIcon />
        </style.StatusBox2>
        <style.StatusBox3>
          <FontAwesomeIcon icon={faXmark} size="xs" />
        </style.StatusBox3>
      </style.StatusBar>
      <style.Margin />
      <TextBox size={25} content={"Total Commits : 365"} />
      <style.Margin />
      <TextBox size={25} content={"Total Stars : 100"} />
      <style.Margin />
      <TextBox size={25} content={"Total Forks : 100"} />
    </style.StatusContainer>
  );
};
export default GithubStatus;
