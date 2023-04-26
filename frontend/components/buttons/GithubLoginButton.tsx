import { Button } from "@mui/material";
import { GitHub } from "@mui/icons-material";
import { createTheme } from "@mui/material/styles";
import { ThemeProvider } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "#333333",
    },
  },
});

const GithubLoginButton = () => {
  return (
    <ThemeProvider theme={theme}>
      <Button
        variant="contained"
        startIcon={<GitHub />}
        style={{ textTransform: "capitalize" }}
        color="primary"
      >
        Login with GitHub
      </Button>
    </ThemeProvider>
  );
};

export default GithubLoginButton;
